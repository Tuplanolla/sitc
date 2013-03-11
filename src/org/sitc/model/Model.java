package org.sitc.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.math3.fraction.BigFraction;
import org.sitc.Part;
import org.sitc.model.xml.XMLInstrument;
import org.sitc.model.xml.XMLInstruments;
import org.sitc.model.xml.XMLNote;
import org.sitc.model.xml.XMLRational;
import org.sitc.model.xml.XMLSequence;
import org.sitc.model.xml.XMLSequences;
import org.sitc.model.xml.XMLString;
import org.sitc.model.xml.XMLTuning;
import org.sitc.model.xml.XMLTunings;

/**
Represents a mutable model.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Model implements Part {
	private final List<Instrument> instruments;
	private final List<Tuning> tunings;
	private final List<Sequence> sequences;
	private Instrument currentInstrument;
	private Tuning currentTuning;
	private Sequence currentSequence;

	/**
	Caches a heavy context.
	**/
	private JAXBContext instrumentContext,
			tuningContext,
			sequenceContext;

	/**
	Creates a model.

	@param arguments The command-line arguments.
	**/
	public Model(final List<java.lang.String> arguments) {
		instruments = new LinkedList<Instrument>();
		tunings = new LinkedList<Tuning>();
		sequences = new LinkedList<Sequence>();
		instrumentContext = null;
		tuningContext = null;
		sequenceContext = null;
	}

	@Override
	public void activate() {}

	/**
	Sorts the instruments alphabetically.
	**/
	public void sortInstruments() {
		Collections.sort(instruments);
	}

	/**
	Sorts the tunings alphabetically.
	**/
	public void sortTunings() {
		Collections.sort(tunings);
	}

	/**
	Sorts the sequences alphabetically.
	**/
	public void sortSequences() {
		Collections.sort(sequences);
	}

	/**
	Loads the instruments from a file.

	@param path The path to the file.
	@throws JAXBException If the file can't be parsed.
	**/
	public void loadInstruments(final java.lang.String path) throws JAXBException {
		final Source source = new StreamSource(new File(path));
		if (instrumentContext == null) instrumentContext = JAXBContext.newInstance(XMLInstruments.class);
		final Unmarshaller unmarshaller = instrumentContext.createUnmarshaller();

		final JAXBElement<XMLInstruments> jaxbElement = unmarshaller.unmarshal(source, XMLInstruments.class);
		final XMLInstruments xmlInstruments = jaxbElement.getValue();

		instruments.clear();
		final List<XMLInstrument> xmlInstrumentList = xmlInstruments.getInstrument();
		for (final XMLInstrument xmlInstrument : xmlInstrumentList) {
			final java.lang.String name = xmlInstrument.getName();
			final BigDecimal maximumInstrumentTension = xmlInstrument.getTension();
			final TuningSystem tuningSystem = null;//TODO Parser.parseTuningSystem(xmlInstrument.getSystem());
			final Instrument instrument = new Instrument(name, maximumInstrumentTension, tuningSystem);
			final List<String> stringList = instrument.getStrings();
			for (final XMLString xmlString : xmlInstrument.getStrings().getString()) {
				final BigDecimal vibratingLength = xmlString.getLength();
				final BigDecimal linearDensity = xmlString.getDensity();
				final BigDecimal maximumStringTension = xmlString.getTension();
				final String string = new String(vibratingLength, linearDensity, maximumStringTension);
				stringList.add(string);
			}
			instruments.add(instrument);
		}

		sortInstruments();
	}

	/**
	Saves the instruments to a file.

	@param path The path to the file.
	@throws JAXBException If the file can't be formatted.
	**/
	public void saveInstruments(final java.lang.String path) throws JAXBException {
		final Result result = new StreamResult(new File(path));
		if (instrumentContext == null) instrumentContext = JAXBContext.newInstance(XMLInstruments.class);
		final Marshaller marshaller = instrumentContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		final XMLInstruments xmlInstruments = new XMLInstruments();
		final List<XMLInstrument> xmlInstrumentList = xmlInstruments.getInstrument();
		for (final Instrument instrument : instruments) {
			final java.lang.String xmlName = instrument.getName();
			final BigDecimal xmlMaximumInstrumentTension = instrument.getMaximumTension();
			final XMLInstrument.System xmlTuningSystem = null;//TODO Formatter.formatTuningSystem(instrument.getTuningSystem());
			final XMLInstrument.Strings xmlStrings = new XMLInstrument.Strings();
			final List<XMLString> xmlStringList = xmlStrings.getString();
			for (final String string : instrument.getStrings()) {
				final BigDecimal xmlVibratingLength = string.getVibratingLength();
				final BigDecimal xmlLinearDensity = string.getLinearDensity();
				final BigDecimal xmlMaximumStringTension = string.getMaximumTension();
				final XMLString xmlString = new XMLString();
				xmlString.setLength(xmlVibratingLength);
				xmlString.setDensity(xmlLinearDensity);
				xmlString.setTension(xmlMaximumStringTension);
				xmlStringList.add(xmlString);
			}
			final XMLInstrument xmlInstrument = new XMLInstrument();
			xmlInstrument.setName(xmlName);
			xmlInstrument.setTension(xmlMaximumInstrumentTension);
			xmlInstrument.setSystem(xmlTuningSystem);
			xmlInstrument.setStrings(xmlStrings);
			xmlInstrumentList.add(xmlInstrument);
		}

		final JAXBElement<XMLInstruments> jaxbElement = new JAXBElement<XMLInstruments>(new QName("instruments"), XMLInstruments.class, xmlInstruments);
		marshaller.marshal(jaxbElement, result);
	}

	/**
	Loads the tunings from a file.

	@param path The path to the file.
	@throws JAXBException If the file can't be parsed.
	**/
	public void loadTunings(final java.lang.String path) throws JAXBException {
		final Source source = new StreamSource(new File(path));
		if (tuningContext == null) tuningContext = JAXBContext.newInstance(XMLTunings.class);
		final Unmarshaller unmarshaller = tuningContext.createUnmarshaller();

		final JAXBElement<XMLTunings> jaxbElement = unmarshaller.unmarshal(source, XMLTunings.class);
		final XMLTunings xmlTunings = jaxbElement.getValue();

		tunings.clear();
		final List<XMLTuning> xmlTuningList = xmlTunings.getTuning();
		for (final XMLTuning xmlTuning : xmlTuningList) {
			final java.lang.String name = xmlTuning.getName();
			final TuningSystem tuningSystem = null;//TODO Parser.parseTuningSystem(xmlTuning.getSystem());
			final Tuning tuning = new Tuning(name, tuningSystem);
			final List<Note> noteList = tuning.getNotes();
			for (final XMLNote xmlNote : xmlTuning.getNotes().getNote()) {
				final BigInteger semitones = xmlNote.getSemitones();
				final XMLRational xmlMicrotones = xmlNote.getMicrotones();
				final Note note;
				if (xmlMicrotones != null) {
					final BigFraction microtones = new BigFraction(xmlMicrotones.getNumerator(), xmlMicrotones.getDenominator());
					note = new Note(semitones, microtones);
				}
				else note = new Note(semitones);
				noteList.add(note);
			}
			tunings.add(tuning);
		}

		sortTunings();
	}

	/**
	Saves the tunings to a file.

	@param path The path to the file.
	@throws JAXBException If the file can't be formatted.
	**/
	public void saveTunings(final java.lang.String path) throws JAXBException {
		final Result result = new StreamResult(new File(path));
		if (tuningContext == null) tuningContext = JAXBContext.newInstance(XMLTunings.class);
		final Marshaller marshaller = tuningContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		final XMLTunings xmlTunings = new XMLTunings();
		final List<XMLTuning> xmlTuningList = xmlTunings.getTuning();
		for (final Tuning tuning : tunings) {
			final java.lang.String xmlName = tuning.getName();
			final XMLTuning.System xmlTuningSystem = null;//TODO Formatter.formatTuningSystem(tuning.getTuningSystem());
			final XMLTuning.Notes xmlNotes = new XMLTuning.Notes();
			final List<XMLNote> xmlNoteList = xmlNotes.getNote();
			for (final Note note : tuning.getNotes()) {
				final BigInteger xmlSemitones = note.getSemitones();
				final BigFraction xmlMicrotones = note.getMicrotones();
				final XMLNote xmlNote = new XMLNote();
				xmlNote.setSemitones(xmlSemitones);
				if (xmlMicrotones != null) {
					final XMLRational xmlRational = new XMLRational();
					xmlRational.setNumerator(xmlMicrotones.getNumerator());
					xmlRational.setDenominator(xmlMicrotones.getDenominator());
					xmlNote.setMicrotones(xmlRational);
				}
				xmlNoteList.add(xmlNote);
			}
			final XMLTuning xmlTuning = new XMLTuning();
			xmlTuning.setName(xmlName);
			xmlTuning.setSystem(xmlTuningSystem);
			xmlTuning.setNotes(xmlNotes);
			xmlTuningList.add(xmlTuning);
		}

		final JAXBElement<XMLTunings> jaxbElement = new JAXBElement<XMLTunings>(new QName("tunings"), XMLTunings.class, xmlTunings);
		marshaller.marshal(jaxbElement, result);
	}

	/**
	Loads the sequences from a file.

	@param path The path to the file.
	@throws JAXBException If the file can't be parsed.
	**/
	public void loadSequences(final java.lang.String path) throws JAXBException {
		final Source source = new StreamSource(new File(path));
		if (sequenceContext == null) sequenceContext = JAXBContext.newInstance(XMLSequences.class);
		final Unmarshaller unmarshaller = sequenceContext.createUnmarshaller();

		final JAXBElement<XMLSequences> jaxbElement = unmarshaller.unmarshal(source, XMLSequences.class);
		final XMLSequences xmlSequences = jaxbElement.getValue();

		sequences.clear();
		final List<XMLSequence> xmlSequenceList = xmlSequences.getSequence();
		for (final XMLSequence xmlSequence : xmlSequenceList) {
			final java.lang.String name = xmlSequence.getName();
			final TuningSystem tuningSystem = null;//TODO Parser.parseTuningSystem(xmlSequence.getSystem());
			final Instrument instrument = null;//TODO lookupInstrument(xmlSequence.getInstrument());
			final Sequence sequence = new Sequence(name, tuningSystem, instrument);
			final List<Tuning> tuningList = sequence.getTunings();
			for (final XMLSequence.Tunings.Tuning xmlTuning : xmlSequence.getTunings().getTuning()) {
				final Tuning tuning = null;//TODO lookupTuning(xmlTuning);
				tuningList.add(tuning);
			}
			sequences.add(sequence);
		}

		sortSequences();
	}

	/**
	Saves the sequences to a file.

	@param path The path to the file.
	@throws JAXBException If the file can't be formatted.
	**/
	public void saveSequences(final java.lang.String path) throws JAXBException {
		final Result result = new StreamResult(new File(path));
		if (sequenceContext == null) sequenceContext = JAXBContext.newInstance(XMLSequences.class);
		final Marshaller marshaller = sequenceContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		final XMLSequences xmlSequences = new XMLSequences();
		final List<XMLSequence> xmlSequenceList = xmlSequences.getSequence();
		for (final Sequence sequence : sequences) {
			final java.lang.String xmlName = sequence.getName();
			final XMLSequence.System xmlTuningSystem = null;//TODO Formatter.formatTuningSystem(sequence.getTuningSystem());
			final XMLSequence.Instrument xmlInstrument = new XMLSequence.Instrument();//TODO wingInstrument(instrument);
			final Instrument instrument = sequence.getInstrument();
			xmlInstrument.setValue(instrument.getName());
			xmlInstrument.setHash(Integer.toString(instrument.hashCode(), 16));
			final XMLSequence.Tunings xmlTunings = new XMLSequence.Tunings();
			final List<XMLSequence.Tunings.Tuning> xmlTuningList = xmlTunings.getTuning();
			for (final Tuning tuning : sequence.getTunings()) {
				final XMLSequence.Tunings.Tuning xmlTuning = new XMLSequence.Tunings.Tuning();//TODO wingTuning(tuning);
				xmlTuning.setValue(tuning.getName());
				xmlTuning.setHash(Integer.toString(tuning.hashCode(), 16));
				xmlTuningList.add(xmlTuning);
			}
			final XMLSequence xmlSequence = new XMLSequence();
			xmlSequence.setName(xmlName);
			xmlSequence.setSystem(xmlTuningSystem);
			xmlSequence.setTunings(xmlTunings);
			xmlSequenceList.add(xmlSequence);
		}

		final JAXBElement<XMLSequences> jaxbElement = new JAXBElement<XMLSequences>(new QName("sequences"), XMLSequences.class, xmlSequences);
		marshaller.marshal(jaxbElement, result);
	}

	@Deprecated
	public static void main(final java.lang.String[] arguments) throws Exception {
		final Model model = new Model(Collections.<java.lang.String>emptyList());

		model.loadInstruments("db/instruments.xml");
		for (final Instrument instrument : model.instruments) {
			System.out.println(instrument);
		}
		final Instrument instrument = new Instrument("Test Bass", EqualTemperament.TWELVE_TONE);
		instrument.getStrings().add(new String(BigDecimal.valueOf(68), BigDecimal.valueOf(5)));
		instrument.getStrings().add(new String(BigDecimal.valueOf(66), BigDecimal.valueOf(4)));
		instrument.getStrings().add(new String(BigDecimal.valueOf(64), BigDecimal.valueOf(3)));
		instrument.getStrings().add(new String(BigDecimal.valueOf(62), BigDecimal.valueOf(2)));
		model.instruments.add(instrument);
		model.saveInstruments("db/test.xml");

		model.loadTunings("db/tunings.xml");
		for (final Tuning tuning : model.tunings) {
			System.out.println(tuning);
		}
		model.saveTunings("db/test.xml");

		model.loadSequences("db/sequences.xml");
		for (final Sequence sequence : model.sequences) {
			System.out.println(sequence);
		}
		model.saveSequences("db/test.xml");
	}

	/**
	@return The instruments.
	**/
	public List<Instrument> getInstruments() {
		return instruments;
	}

	/**
	@return The tunings.
	**/
	public List<Tuning> getTunings() {
		return tunings;
	}

	/**
	@return The sequences.
	**/
	public List<Sequence> getSequences() {
		return sequences;
	}

	/**
	@return The current instrument.
	**/
	public Instrument getCurrentInstrument() {
		return currentInstrument;
	}

	/**
	@param currentInstrument The new current instrument.
	**/
	public void setCurrentInstrument(final Instrument currentInstrument) {
		this.currentInstrument = currentInstrument;
	}

	/**
	@return The current tuning.
	**/
	public Tuning getCurrentTuning() {
		return currentTuning;
	}

	/**
	@param currentTuning The new current tuning.
	**/
	public void setCurrentTuning(final Tuning currentTuning) {
		this.currentTuning = currentTuning;
	}

	/**
	@return The current sequence.
	**/
	public Sequence getCurrentSequence() {
		return currentSequence;
	}

	/**
	@param currentSequence The new current sequence.
	**/
	public void setCurrentSequence(final Sequence currentSequence) {
		this.currentSequence = currentSequence;
	}
}

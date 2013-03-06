package org.sitdb.model;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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

import org.sitdb.Part;
import org.sitdb.model.xml.*;

/**
Represents a mutable model.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Model implements Part {
	private List<Instrument> instruments;
	private List<Tuning> tunings;
	private List<Sequence> sequences;
	private Instrument currentInstrument;
	private Tuning currentTuning;
	private Sequence currentSequence;

	/**
	Creates a model.

	@param arguments The command-line arguments.
	**/
	public Model(final List<java.lang.String> arguments) {}

	@Override
	public void activate() {}

	/**
	Loads the instruments from a file.

	@param path The path to the file.
	@throws JAXBException If the file can't be parsed.
	**/
	public void loadInstruments(final java.lang.String path) throws JAXBException {
		final Source source = new StreamSource(new File(path));
		final JAXBContext context = JAXBContext.newInstance(XMLInstruments.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();

		final JAXBElement<XMLInstruments> jaxbElement = unmarshaller.unmarshal(source, XMLInstruments.class);
		final XMLInstruments xmlInstruments = jaxbElement.getValue();

		final List<XMLInstrument> xmlInstrumentList = xmlInstruments.getInstrument();
		instruments = new ArrayList<Instrument>(xmlInstrumentList.size());
		for (final XMLInstrument xmlInstrument : xmlInstrumentList) {
			final java.lang.String name = xmlInstrument.getName();
			final BigDecimal maximumInstrumentTension = xmlInstrument.getTension();
			final TuningSystem tuningSystem = null;//TODO Parser.parseTuningSystem(xmlInstrument.getSystem().getValue());
			final Instrument instrument = new Instrument(name, maximumInstrumentTension, tuningSystem);
			for (final XMLString xmlString : xmlInstrument.getStrings().getString()) {
				final BigDecimal vibratingLength = xmlString.getLength();
				final BigDecimal linearDensity = xmlString.getDensity();
				final BigDecimal maximumStringTension = xmlString.getTension();
				final String string = new String(vibratingLength, linearDensity, maximumStringTension);
				instrument.getStrings().add(string);
			}
			instruments.add(instrument);
		}
	}

	/**
	Saves the instruments to a file.

	@param path The path to the file.
	@throws JAXBException If the file can't be formatted.
	**/
	public void saveInstruments(final java.lang.String path) throws JAXBException {
		final Result result = new StreamResult(new File(path));
		final JAXBContext context = JAXBContext.newInstance(XMLInstruments.class);
		final Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		final XMLInstruments xmlInstruments = new XMLInstruments();
		final List<XMLInstrument> xmlInstrumentList = xmlInstruments.getInstrument();
		for (final Instrument instrument : instruments) {
			final java.lang.String name = instrument.getName();
			final BigDecimal maximumInstrumentTension = instrument.getMaximumTension();
			final XMLInstrument.System tuningSystem = null;//TODO Formatter.formatTuningSystem(instrument.tuningSystem);
			final XMLInstrument.Strings strings = new XMLInstrument.Strings();
			final List<XMLString> stringList = strings.getString();
			for (final String string : instrument.getStrings()) {
				final BigDecimal vibratingLength = string.getVibratingLength();
				final BigDecimal linearDensity = string.getLinearDensity();
				final BigDecimal maximumStringTension = string.getMaximumTension();
				final XMLString xmlString = new XMLString();
				xmlString.setLength(vibratingLength);
				xmlString.setDensity(linearDensity);
				xmlString.setTension(maximumStringTension);
				stringList.add(xmlString);
			}
			final XMLInstrument xmlInstrument = new XMLInstrument();
			xmlInstrument.setName(name);
			xmlInstrument.setTension(maximumInstrumentTension);
			xmlInstrument.setSystem(tuningSystem);
			xmlInstrument.setStrings(strings);
			xmlInstrumentList.add(xmlInstrument);
		}

		final JAXBElement<XMLInstruments> jaxbElement = new JAXBElement<XMLInstruments>(new QName("instruments"), XMLInstruments.class, xmlInstruments);
		marshaller.marshal(jaxbElement, result);
	}

	public void loadTunings(final java.lang.String path) {}

	public void saveTunings(final java.lang.String path) {}

	public void loadSequences(final java.lang.String path) {}

	public void saveSequences(final java.lang.String path) {}

	@Deprecated
	public static void main(final java.lang.String[] arguments) throws Exception {
		final Model model = new Model(Collections.<java.lang.String>emptyList());
		model.loadInstruments("db/instruments.xml");
		for (final Instrument string : model.instruments) {
			System.out.println(string);
		}
		final Instrument instrument = new Instrument("Test Bass", EqualTemperament.TWELVE_TONE);
		instrument.getStrings().add(new String(BigDecimal.valueOf(68), BigDecimal.valueOf(5)));
		instrument.getStrings().add(new String(BigDecimal.valueOf(66), BigDecimal.valueOf(4)));
		instrument.getStrings().add(new String(BigDecimal.valueOf(64), BigDecimal.valueOf(3)));
		instrument.getStrings().add(new String(BigDecimal.valueOf(62), BigDecimal.valueOf(2)));
		model.instruments.add(instrument);
		model.saveInstruments("db/test.xml");
	}
}

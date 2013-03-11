package org.sitc.model.xml;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
@XmlRegistry
public class ObjectFactory {
    private final static QName _Sequences_QNAME = new QName("", "sequences");
    private final static QName _Tunings_QNAME = new QName("", "tunings");
    private final static QName _Instruments_QNAME = new QName("", "instruments");
    public ObjectFactory() {
    }
    public XMLInstrument createXMLInstrument() {
        return new XMLInstrument();
    }
    public XMLSequence createXMLSequence() {
        return new XMLSequence();
    }
    public XMLSequence.Tunings createXMLSequenceTunings() {
        return new XMLSequence.Tunings();
    }
    public XMLTuning createXMLTuning() {
        return new XMLTuning();
    }
    public XMLSequences createXMLSequences() {
        return new XMLSequences();
    }
    public XMLTunings createXMLTunings() {
        return new XMLTunings();
    }
    public XMLInstruments createXMLInstruments() {
        return new XMLInstruments();
    }
    public XMLNote createXMLNote() {
        return new XMLNote();
    }
    public XMLString createXMLString() {
        return new XMLString();
    }
    public XMLRational createXMLRational() {
        return new XMLRational();
    }
    public XMLInstrument.System createXMLInstrumentSystem() {
        return new XMLInstrument.System();
    }
    public XMLInstrument.Strings createXMLInstrumentStrings() {
        return new XMLInstrument.Strings();
    }
    public XMLSequence.System createXMLSequenceSystem() {
        return new XMLSequence.System();
    }
    public XMLSequence.Instrument createXMLSequenceInstrument() {
        return new XMLSequence.Instrument();
    }
    public XMLSequence.Tunings.Tuning createXMLSequenceTuningsTuning() {
        return new XMLSequence.Tunings.Tuning();
    }
    public XMLTuning.System createXMLTuningSystem() {
        return new XMLTuning.System();
    }
    public XMLTuning.Notes createXMLTuningNotes() {
        return new XMLTuning.Notes();
    }
    @XmlElementDecl(namespace = "", name = "sequences")
    public JAXBElement<XMLSequences> createSequences(XMLSequences value) {
        return new JAXBElement<XMLSequences>(_Sequences_QNAME, XMLSequences.class, null, value);
    }
    @XmlElementDecl(namespace = "", name = "tunings")
    public JAXBElement<XMLTunings> createTunings(XMLTunings value) {
        return new JAXBElement<XMLTunings>(_Tunings_QNAME, XMLTunings.class, null, value);
    }
    @XmlElementDecl(namespace = "", name = "instruments")
    public JAXBElement<XMLInstruments> createInstruments(XMLInstruments value) {
        return new JAXBElement<XMLInstruments>(_Instruments_QNAME, XMLInstruments.class, null, value);
    }
}

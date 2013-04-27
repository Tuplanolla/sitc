package org.sitc.models.standardmodel.xml;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLNote", propOrder = {
})
@SuppressWarnings("all")
public class XMLNote {
    @XmlElement(required = true)
    protected BigInteger semitones;
    protected XMLRational microtones;
    public BigInteger getSemitones() {
        return semitones;
    }
    public void setSemitones(BigInteger value) {
        this.semitones = value;
    }
    public XMLRational getMicrotones() {
        return microtones;
    }
    public void setMicrotones(XMLRational value) {
        this.microtones = value;
    }
}

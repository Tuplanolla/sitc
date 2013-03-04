package org.sitdb.model.db;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLNote", propOrder = {
})
public class XMLNote {
    @XmlElement(required = true)
    protected BigDecimal semitones;
    protected XMLRational microtones;
    public BigDecimal getSemitones() {
        return semitones;
    }
    public void setSemitones(BigDecimal value) {
        this.semitones = value;
    }
    public XMLRational getMicrotones() {
        return microtones;
    }
    public void setMicrotones(XMLRational value) {
        this.microtones = value;
    }
}

package org.sitc.model.xml;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLString", propOrder = {
})
@SuppressWarnings("all")
public class XMLString {
    @XmlElement(required = true)
    protected BigDecimal length;
    @XmlElement(required = true)
    protected BigDecimal density;
    protected BigDecimal tension;
    public BigDecimal getLength() {
        return length;
    }
    public void setLength(BigDecimal value) {
        this.length = value;
    }
    public BigDecimal getDensity() {
        return density;
    }
    public void setDensity(BigDecimal value) {
        this.density = value;
    }
    public BigDecimal getTension() {
        return tension;
    }
    public void setTension(BigDecimal value) {
        this.tension = value;
    }
}

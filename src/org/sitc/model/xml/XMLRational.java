package org.sitc.model.xml;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLRational", propOrder = {
})
public class XMLRational {
    @XmlElement(required = true)
    protected BigInteger numerator;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger denominator;
    public BigInteger getNumerator() {
        return numerator;
    }
    public void setNumerator(BigInteger value) {
        this.numerator = value;
    }
    public BigInteger getDenominator() {
        return denominator;
    }
    public void setDenominator(BigInteger value) {
        this.denominator = value;
    }
}

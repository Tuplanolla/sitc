package org.sitdb.model.db;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLInstrument", propOrder = {
})
public class XMLInstrument {
    @XmlElement(required = true)
    protected String name;
    protected BigDecimal tension;
    protected XMLInstrument.System system;
    @XmlElement(required = true)
    protected XMLInstrument.Strings strings;
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }
    public BigDecimal getTension() {
        return tension;
    }
    public void setTension(BigDecimal value) {
        this.tension = value;
    }
    public XMLInstrument.System getSystem() {
        return system;
    }
    public void setSystem(XMLInstrument.System value) {
        this.system = value;
    }
    public XMLInstrument.Strings getStrings() {
        return strings;
    }
    public void setStrings(XMLInstrument.Strings value) {
        this.strings = value;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "string"
    })
    public static class Strings {
        @XmlElement(required = true)
        protected List<XMLString> string;
        public List<XMLString> getString() {
            if (string == null) {
                string = new ArrayList<XMLString>();
            }
            return this.string;
        }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class System {
        @XmlValue
        protected String value;
        @XmlAttribute(name = "hash")
        protected String hash;
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public String getHash() {
            return hash;
        }
        public void setHash(String value) {
            this.hash = value;
        }
    }
}

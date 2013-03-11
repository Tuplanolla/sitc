package org.sitc.model.xml;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLSequence", propOrder = {
})
public class XMLSequence {
    @XmlElement(required = true)
    protected String name;
    protected XMLSequence.System system;
    protected XMLSequence.Instrument instrument;
    @XmlElement(required = true)
    protected XMLSequence.Tunings tunings;
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }
    public XMLSequence.System getSystem() {
        return system;
    }
    public void setSystem(XMLSequence.System value) {
        this.system = value;
    }
    public XMLSequence.Instrument getInstrument() {
        return instrument;
    }
    public void setInstrument(XMLSequence.Instrument value) {
        this.instrument = value;
    }
    public XMLSequence.Tunings getTunings() {
        return tunings;
    }
    public void setTunings(XMLSequence.Tunings value) {
        this.tunings = value;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Instrument {
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
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tuning"
    })
    public static class Tunings {
        @XmlElement(required = true)
        protected List<XMLSequence.Tunings.Tuning> tuning;
        @XmlAttribute(name = "span", required = true)
        protected int span;
        public List<XMLSequence.Tunings.Tuning> getTuning() {
            if (tuning == null) {
                tuning = new ArrayList<XMLSequence.Tunings.Tuning>();
            }
            return this.tuning;
        }
        public int getSpan() {
            return span;
        }
        public void setSpan(int value) {
            this.span = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Tuning {
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
}

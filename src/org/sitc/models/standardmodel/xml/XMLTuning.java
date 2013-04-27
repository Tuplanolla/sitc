package org.sitc.models.standardmodel.xml;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLTuning", propOrder = {
})
@SuppressWarnings("all")
public class XMLTuning {
    @XmlElement(required = true)
    protected String name;
    protected XMLTuning.System system;
    @XmlElement(required = true)
    protected XMLTuning.Notes notes;
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }
    public XMLTuning.System getSystem() {
        return system;
    }
    public void setSystem(XMLTuning.System value) {
        this.system = value;
    }
    public XMLTuning.Notes getNotes() {
        return notes;
    }
    public void setNotes(XMLTuning.Notes value) {
        this.notes = value;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "note"
    })
    @SuppressWarnings("all")
    public static class Notes {
        @XmlElement(required = true)
        protected List<XMLNote> note;
        public List<XMLNote> getNote() {
            if (note == null) {
                note = new ArrayList<XMLNote>();
            }
            return this.note;
        }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    @SuppressWarnings("all")
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

package org.sitdb.model.db;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLSequences", propOrder = {
    "sequence"
})
public class XMLSequences {
    @XmlElement(required = true)
    protected List<XMLSequence> sequence;
    public List<XMLSequence> getSequence() {
        if (sequence == null) {
            sequence = new ArrayList<XMLSequence>();
        }
        return this.sequence;
    }
}

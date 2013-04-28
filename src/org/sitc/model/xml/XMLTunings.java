package org.sitc.model.xml;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLTunings", propOrder = {
    "tuning"
})
@SuppressWarnings("all")
public class XMLTunings {
    @XmlElement(required = true)
    protected List<XMLTuning> tuning;
    public List<XMLTuning> getTuning() {
        if (tuning == null) {
            tuning = new ArrayList<XMLTuning>();
        }
        return this.tuning;
    }
}

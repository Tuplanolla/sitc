package org.sitc.model.xml;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLInstruments", propOrder = {
    "instrument"
})
@SuppressWarnings("all")
public class XMLInstruments {
    @XmlElement(required = true)
    protected List<XMLInstrument> instrument;
    public List<XMLInstrument> getInstrument() {
        if (instrument == null) {
            instrument = new ArrayList<XMLInstrument>();
        }
        return this.instrument;
    }
}

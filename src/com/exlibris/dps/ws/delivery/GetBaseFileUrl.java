
package com.exlibris.dps.ws.delivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBaseFileUrl complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="getBaseFileUrl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dvs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBaseFileUrl", propOrder = {
    "dvs"
})
public class GetBaseFileUrl {

    protected String dvs;

    /**
     * Gets the value of the dvs property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDvs() {
        return dvs;
    }

    /**
     * Sets the value of the dvs property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDvs(String value) {
        this.dvs = value;
    }

}

package ru.job4.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "folder")
@XmlAccessorType(XmlAccessType.FIELD)
public class Folder {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private long size;
    @XmlAttribute
    private boolean backup;
    @XmlElement
    private User owner;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] attributes;

    public Folder() {
    }

    public Folder(String name, long size, boolean backup, User owner, String... statuses) {
        this.name = name;
        this.size = size;
        this.backup = backup;
        this.owner = owner;
        this.attributes = statuses;
    }

    @Override
    public String toString() {
        return "Folder{" + "name='" + name + '\''
                + ", size=" + size
                + ", backup=" + backup
                + ", owner=" + owner
                + ", statuses=" + Arrays.toString(attributes) + '}';
    }
}

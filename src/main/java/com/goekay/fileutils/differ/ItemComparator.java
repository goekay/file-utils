package com.goekay.fileutils.differ;

import com.goekay.fileutils.core.FileMetaData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Main building block for file comparison based on collected values during traversal. More precisely,
 * the methods equals() and hashCode() of {@link FileMetaData} are of particular importance, since we rely on
 * {@link HashSet}.
 *
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 12.12.2015
 */
public class ItemComparator {

    @Setter private List<FileMetaData> first;
    @Setter private List<FileMetaData> other;

    @Getter private List<FileMetaData> onlyInFirst;
    @Getter private List<FileMetaData> onlyInOther;

    public void run() {
        onlyInFirst = calcOnlyInFirst();
        onlyInOther = calcOnlyInOther();
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private List<FileMetaData> calcOnlyInFirst() {
        HashSet<FileMetaData> difference = new HashSet<>(first);
        difference.removeAll(other);
        return postProcess(difference);
    }

    private List<FileMetaData> calcOnlyInOther() {
        HashSet<FileMetaData> difference = new HashSet<>(other);
        difference.removeAll(first);
        return postProcess(difference);
    }

    private List<FileMetaData> postProcess(Set<FileMetaData> difference) {
        ArrayList<FileMetaData> myList = new ArrayList<>(difference);
        Collections.sort(myList);
        return myList;
    }
}
package com.bparent.workshop.unittests.util;

import com.bparent.workshop.unittests.bean.Frame;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreFormatter {

    private ScoreFormatter() {}

    public static String formatScore(List<Frame> frames) {
        String firstLine = frames.stream().map(frame -> StringUtils.rightPad(frame.toString(), 10)).collect(Collectors.joining());
        String secondLine = frames.stream().map(frame -> StringUtils.rightPad(frame.getDisplayScore(), 10)).collect(Collectors.joining());

        return String.format("%s\n%s", firstLine, secondLine);
    }

}

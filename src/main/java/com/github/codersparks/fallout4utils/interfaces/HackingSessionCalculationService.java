package com.github.codersparks.fallout4utils.interfaces;

import com.github.codersparks.fallout4utils.data.HackingSession;
import com.github.codersparks.fallout4utils.exception.HackingSessionException;

import java.util.List;

/**
 * Created by codersparks on 15/11/2015.
 */
public interface HackingSessionCalculationService {

    HackingSession calculateLevenshteinDistances(HackingSession session) throws HackingSessionException;

    List<String> calculateBestChoice(final HackingSession hackingSession) throws HackingSessionException;

    HackingSession updateLikeness (HackingSession session, String candidate, int likeness) throws HackingSessionException;
}

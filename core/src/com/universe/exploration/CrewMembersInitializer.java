/**
 *
 */
package com.universe.exploration;

import com.universe.exploration.common.tools.FileReader;
import com.universe.exploration.crewmember.CrewmemberProfile;
import com.universe.exploration.crewmember.CrewmemberSex;
import com.universe.exploration.crewmember.Nationality;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Creates all the possible profiles for crewmembers.
 * </p>
 * <p>
 * In case new files for potential crewmember names appear, see
 * {@link #createNameProfileFilepathList()} and make appropriate updates to
 * match the newly created file.
 * </p>
 *
 * @author 28.2.2016 Teemu Puurunen
 */
public class CrewMembersInitializer {
    private static final String ROOT = "crewmemberprofiles";
    private static final String ROOT_PATH_MALE = ROOT + File.separator + "male" + File.separator;
    private static final String ROOT_PATH_FEMALE = ROOT + File.separator + "female" + File.separator;

    private static final List<CrewmemberProfileSource> CREWMEMBER_PROFILE_FILES = createNameProfileFilepathList();

    private List<CrewmemberProfile> maleProfiles = new ArrayList<>();
    private List<CrewmemberProfile> femaleProfiles = new ArrayList<>();

    public CrewMembersInitializer() throws IOException {
        FileReader fh = new FileReader();

        for (CrewmemberProfileSource filepath : CREWMEMBER_PROFILE_FILES) {
            for (String name : fh.readTextFile(filepath.getTemplatePath())) {
                add(new CrewmemberProfile(filepath.getCrewmemberSex(), filepath.getNationality(), name));
            }
        }
    }

    private void add(CrewmemberProfile profile) {
        if (profile.getSex().equals(CrewmemberSex.MALE)) {
            maleProfiles.add(profile);
        } else {
            femaleProfiles.add(profile);
        }
    }

    private static List<CrewmemberProfileSource> createNameProfileFilepathList() {
        List<CrewmemberProfileSource> profileFilepathList = new ArrayList<CrewmemberProfileSource>();

        // Male profiles.
        profileFilepathList.add(new CrewmemberProfileSource(CrewmemberSex.MALE, Nationality.AMERICAN, ROOT_PATH_MALE + "american"));
        profileFilepathList.add(new CrewmemberProfileSource(CrewmemberSex.MALE, Nationality.ENGLISH, ROOT_PATH_MALE + "english"));
        profileFilepathList.add(new CrewmemberProfileSource(CrewmemberSex.MALE, Nationality.FINNISH, ROOT_PATH_MALE + "finnish"));

        // Female profiles.
        profileFilepathList.add(new CrewmemberProfileSource(CrewmemberSex.FEMALE, Nationality.AMERICAN, ROOT_PATH_FEMALE + "american"));
        profileFilepathList.add(new CrewmemberProfileSource(CrewmemberSex.FEMALE, Nationality.CHINESE, ROOT_PATH_FEMALE + "chinese"));
        profileFilepathList.add(new CrewmemberProfileSource(CrewmemberSex.FEMALE, Nationality.ENGLISH, ROOT_PATH_FEMALE + "english"));
        profileFilepathList.add(new CrewmemberProfileSource(CrewmemberSex.FEMALE, Nationality.FINNISH, ROOT_PATH_FEMALE + "finnish"));
        profileFilepathList.add(new CrewmemberProfileSource(CrewmemberSex.FEMALE, Nationality.RUSSIAN, ROOT_PATH_FEMALE + "russian"));

        return profileFilepathList;
    }

    /**
     * @return the maleProfiles
     */
    public List<CrewmemberProfile> getMaleProfiles() {
        return maleProfiles;
    }

    /**
     * @return the femaleProfiles
     */
    public List<CrewmemberProfile> getFemaleProfiles() {
        return femaleProfiles;
    }

}

/**
 * Describes a source based on which a crewmember can be created.
 *
 * @author 6.12.2016 Teemu Puurunen
 */
class CrewmemberProfileSource {
    private CrewmemberSex crewmemberSex;
    private Nationality nationality;
    private String templatePath;

    /**
     * @param crewmemberSex
     * @param nationality
     * @param templatePath
     */
    public CrewmemberProfileSource(CrewmemberSex crewmemberSex, Nationality nationality, String templatePath) {
        this.crewmemberSex = crewmemberSex;
        this.nationality = nationality;
        this.templatePath = templatePath;
    }

    /**
     * @return the crewmemberSex
     */
    public CrewmemberSex getCrewmemberSex() {
        return crewmemberSex;
    }

    /**
     * @return the nationality
     */
    public Nationality getNationality() {
        return nationality;
    }

    /**
     * @return the templatePath
     */
    public String getTemplatePath() {
        return templatePath;
    }
}
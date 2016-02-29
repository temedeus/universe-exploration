/**
 * 
 */
package com.universe.exploration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.crewmen.CrewmemberProfile;
import com.universe.exploration.crewmen.CrewmemberSex;
import com.universe.exploration.crewmen.Nationality;

/**
 * @author 28.2.2016 Teemu Puurunen
 *
 */
public class CrewMembersInitializer {

    private static final String ROOT_PATH_MALE = "crewmemberprofiles" + File.separator + "male" + File.separator;
    private static final String ROOT_PATH_FEMALE = "crewmemberprofiles" + File.separator + "female" + File.separator;

    private static final List<CrewmemberProfileFilepath> CREWMEMBER_PROFILE_FILES = createNameProfileFilepathList();
    private List<CrewmemberProfile> names = new ArrayList<CrewmemberProfile>();
    
    public CrewMembersInitializer() throws IOException {
	
	FileReader fh = new FileReader();
	for (CrewmemberProfileFilepath filepath : CREWMEMBER_PROFILE_FILES) {
	    fh.readTextFile(filepath.getTemplatePath()).forEach(
		    name -> names.add(new CrewmemberProfile(filepath.getCrewmemberSex(), filepath.getNationality(), name)));
	}
    }

    private static List<CrewmemberProfileFilepath> createNameProfileFilepathList() {
	List<CrewmemberProfileFilepath> profileFilepathList = new ArrayList<CrewmemberProfileFilepath>();

	// Male profiles.
	profileFilepathList.add(new CrewmemberProfileFilepath(CrewmemberSex.MALE, Nationality.AMERICAN, ROOT_PATH_MALE + "american"));
	profileFilepathList.add(new CrewmemberProfileFilepath(CrewmemberSex.MALE, Nationality.ENGLISH, ROOT_PATH_MALE + "english"));
	profileFilepathList.add(new CrewmemberProfileFilepath(CrewmemberSex.MALE, Nationality.FINNISH, ROOT_PATH_MALE + "finnish"));

	// Female profiles.
	profileFilepathList.add(new CrewmemberProfileFilepath(CrewmemberSex.FEMALE, Nationality.AMERICAN, ROOT_PATH_FEMALE + "american"));
	profileFilepathList.add(new CrewmemberProfileFilepath(CrewmemberSex.FEMALE, Nationality.CHINESE, ROOT_PATH_FEMALE + "chinese"));
	profileFilepathList.add(new CrewmemberProfileFilepath(CrewmemberSex.FEMALE, Nationality.ENGLISH, ROOT_PATH_FEMALE + "english"));
	profileFilepathList.add(new CrewmemberProfileFilepath(CrewmemberSex.FEMALE, Nationality.FINNISH, ROOT_PATH_FEMALE + "finnish"));
	profileFilepathList.add(new CrewmemberProfileFilepath(CrewmemberSex.FEMALE, Nationality.RUSSIAN, ROOT_PATH_FEMALE + "russian"));

	return profileFilepathList;
    }

    /**
     * @return the names
     */
    public List<CrewmemberProfile> getNames() {
        return names;
    }
}

class CrewmemberProfileFilepath {

    private CrewmemberSex crewmemberSex;
    private Nationality nationality;
    private String templatePath;

    /**
     * @param crewmemberSex
     * @param nationality
     * @param templatePath
     */
    public CrewmemberProfileFilepath(CrewmemberSex crewmemberSex, Nationality nationality, String templatePath) {
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
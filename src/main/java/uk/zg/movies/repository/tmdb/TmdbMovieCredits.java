package uk.zg.movies.repository.tmdb;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TmdbMovieCredits {

	@SerializedName("crew")
	private List<TmdbCrewMember> crewMembers;
	
	public TmdbMovieCredits() {
		// gson uses it
	}

	public TmdbMovieCredits(List<TmdbCrewMember> crewMembers) {
		this.crewMembers = crewMembers;
	}

	public List<TmdbCrewMember> getCrewMembers() {
		return crewMembers;
	}

	public void setCrewMembers(List<TmdbCrewMember> crewMembers) {
		this.crewMembers = crewMembers;
	}

}
package uk.zg.movies.repository.tmdb;

public class TmdbCrewMember {

	private String id;
	
	private String job;
	
	private String name;

	public TmdbCrewMember() {
		// gson uses it
	}
	
	public TmdbCrewMember(String id, String job, String name) {
		this.id = id;
		this.job = job;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
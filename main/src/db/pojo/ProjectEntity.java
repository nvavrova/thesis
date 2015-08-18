package db.pojo;

import javax.persistence.*;

/**
 * Created by Nik on 18-08-2015
 */
@Entity
@Table(name = "project", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "PROJECT_SEQ", sequenceName = "project_id_seq", allocationSize = 1)
public class ProjectEntity {
	private int id;
	private String link;
	private String owner;
	private String repo;
	private String diskLocation;
	private String bugLabel;
	private boolean process;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_SEQ")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "link")
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Basic
	@Column(name = "owner")
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Basic
	@Column(name = "repo")
	public String getRepo() {
		return this.repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	@Basic
	@Column(name = "disk_location")
	public String getDiskLocation() {
		return this.diskLocation;
	}

	public void setDiskLocation(String diskLocation) {
		this.diskLocation = diskLocation;
	}

	@Basic
	@Column(name = "bug_label")
	public String getBugLabel() {
		return this.bugLabel;
	}

	public void setBugLabel(String bugLabel) {
		this.bugLabel = bugLabel;
	}

	@Basic
	@Column(name = "process")
	public boolean isProcess() {
		return this.process;
	}

	public void setProcess(boolean process) {
		this.process = process;
	}

	@Override
	public int hashCode() {
		int result = this.id;
		result = 31 * result + (this.link != null ? this.link.hashCode() : 0);
		result = 31 * result + (this.owner != null ? this.owner.hashCode() : 0);
		result = 31 * result + (this.repo != null ? this.repo.hashCode() : 0);
		result = 31 * result + (this.diskLocation != null ? this.diskLocation.hashCode() : 0);
		result = 31 * result + (this.process ? 1 : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}

		ProjectEntity that = (ProjectEntity) o;

		if (this.id != that.id) {
			return false;
		}
		if (this.process != that.process) {
			return false;
		}
		if (this.link != null ? !this.link.equals(that.link) : that.link != null) {
			return false;
		}
		if (this.owner != null ? !this.owner.equals(that.owner) : that.owner != null) {
			return false;
		}
		if (this.repo != null ? !this.repo.equals(that.repo) : that.repo != null) {
			return false;
		}
		if (this.diskLocation != null ? !this.diskLocation.equals(that.diskLocation) : that.diskLocation != null) {
			return false;
		}

		return true;
	}
}

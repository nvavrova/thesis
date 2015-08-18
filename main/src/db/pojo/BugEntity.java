package db.pojo;

import javax.persistence.*;

/**
 * Created by Nik on 18-08-2015
 */
@Entity
@Table(name = "bug", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "BUG_SEQ", sequenceName = "bug_id_seq", allocationSize = 1)
public class BugEntity {
	private int id;
	private String gitId;
	private ProjectEntity projectEntity;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUG_SEQ")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "git_id")
	public String getGitId() {
		return this.gitId;
	}

	public void setGitId(String gitId) {
		this.gitId = gitId;
	}

	@ManyToOne
	@JoinColumn(name = "project_id")
	public ProjectEntity getProjectEntity() {
		return this.projectEntity;
	}

	public void setProjectEntity(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	@Override
	public int hashCode() {
		int result = this.id;
		result = 31 * result + (this.gitId != null ? this.gitId.hashCode() : 0);
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

		BugEntity bugEntity = (BugEntity) o;

		if (this.id != bugEntity.id) {
			return false;
		}
		if (this.gitId != null ? !this.gitId.equals(bugEntity.gitId) : bugEntity.gitId != null) {
			return false;
		}

		return true;
	}
}

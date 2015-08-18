package db.pojo;

import javax.persistence.*;

/**
 * Created by Nik on 18-08-2015
 */
@Entity
@Table(name = "bug_fix_commit", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "BUG_FIX_SEQ", sequenceName = "bug_fix_commit_id_seq", allocationSize = 1)
public class BugFixCommitEntity {
	private int id;
	private String commitSha;
	private BugEntity bugEntity;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUG_FIX_SEQ")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "commit_sha")
	public String getCommitSha() {
		return this.commitSha;
	}

	public void setCommitSha(String commitSha) {
		this.commitSha = commitSha;
	}

	@ManyToOne
	@JoinColumn(name = "bug_id")
	public BugEntity getBugEntity() {
		return this.bugEntity;
	}

	public void setBugEntity(BugEntity bugEntity) {
		this.bugEntity = bugEntity;
	}

	@Override
	public int hashCode() {
		int result = this.id;
		result = 31 * result + (this.commitSha != null ? this.commitSha.hashCode() : 0);
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

		BugFixCommitEntity that = (BugFixCommitEntity) o;

		if (this.id != that.id) {
			return false;
		}
		if (this.commitSha != null ? !this.commitSha.equals(that.commitSha) : that.commitSha != null) {
			return false;
		}

		return true;
	}
}

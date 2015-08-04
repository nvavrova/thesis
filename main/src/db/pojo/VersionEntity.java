package db.pojo;

import javax.persistence.*;

/**
 * Created by Nik on 03-08-2015
 */
@Entity
@Table(name = "version", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "VE_SEQ", sequenceName = "version_id_seq", allocationSize = 1)
public class VersionEntity {
	private int id;
	private String commitSha;
	private RunInfoEntity runInfoEntity;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VE_SEQ")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "commit_sha")
	public String getCommitSha() {
		return commitSha;
	}

	public void setCommitSha(String commitSha) {
		this.commitSha = commitSha;
	}

	@ManyToOne
	@JoinColumn(name = "run_info_id")
	public RunInfoEntity getRunInfoEntity() {
		return this.runInfoEntity;
	}

	public void setRunInfoEntity(RunInfoEntity runInfoEntity) {
		this.runInfoEntity = runInfoEntity;
	}


	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (commitSha != null ? commitSha.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		VersionEntity that = (VersionEntity) o;

		if (id != that.id) {
			return false;
		}
		if (commitSha != null ? !commitSha.equals(that.commitSha) : that.commitSha != null) {
			return false;
		}

		return true;
	}
}

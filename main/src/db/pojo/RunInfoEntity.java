package db.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nik on 28-07-2015
 */
@Entity
@Table(name = "run_info", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "RI_SEQ", sequenceName = "run_info_id_seq", allocationSize = 1)
public class RunInfoEntity {
	private Integer id;
	private Timestamp runtime;
	private ProjectEntity projectEntity;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RI_SEQ")
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic
	@Column(name = "runtime")
	public Timestamp getRuntime() {
		return this.runtime;
	}

	public void setRuntime(Timestamp runtime) {
		this.runtime = runtime;
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
		result = 31 * result + (this.runtime != null ? this.runtime.hashCode() : 0);
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

		RunInfoEntity that = (RunInfoEntity) o;

		if (this.id != that.id) {
			return false;
		}
		if (this.runtime != null ? !this.runtime.equals(that.runtime) : that.runtime != null) {
			return false;
		}

		return true;
	}
}

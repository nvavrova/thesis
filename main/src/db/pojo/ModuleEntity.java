package db.pojo;

import javax.persistence.*;

/**
 * Created by Nik on 28-07-2015
 */
@Entity
@Table(name = "module", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "MOD_SEQ", sequenceName = "module_id_seq", allocationSize = 1)
public class ModuleEntity {
	private Integer id;
	private RunInfoEntity runInfoEntity;
	private String path;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOD_SEQ")
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "run_info_id")
	public RunInfoEntity getRunInfoEntity() {
		return this.runInfoEntity;
	}

	public void setRunInfoEntity(RunInfoEntity runInfoEntity) {
		this.runInfoEntity = runInfoEntity;
	}

	@Basic
	@Column(name = "path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		int result = this.id;
		result = 31 * result + (this.path != null ? this.path.hashCode() : 0);
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

		ModuleEntity that = (ModuleEntity) o;

		if (this.id != that.id) {
			return false;
		}
		if (this.path != null ? !this.path.equals(that.path) : that.path != null) {
			return false;
		}

		return true;
	}
}

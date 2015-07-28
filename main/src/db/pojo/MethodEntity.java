package db.pojo;

import javax.persistence.*;

/**
 * Created by Nik on 28-07-2015
 */
@Entity
@Table(name = "method", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "MTHD_SEQ", sequenceName = "method_id_seq", allocationSize = 1)
public class MethodEntity {
	private Integer id;
	private ClassEntity classEntity;
	private String name;
	private Integer loc;
	private Short nrParams;
	private Boolean isAccessor;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MTHD_SEQ")
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "class_id")
	public ClassEntity getClassEntity() {
		return this.classEntity;
	}

	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "loc")
	public Integer getLoc() {
		return this.loc;
	}

	public void setLoc(Integer loc) {
		this.loc = loc;
	}

	@Basic
	@Column(name = "nr_params")
	public Short getNrParams() {
		return this.nrParams;
	}

	public void setNrParams(Short nrParams) {
		this.nrParams = nrParams;
	}

	@Basic
	@Column(name = "is_accessor")
	public Boolean getIsAccessor() {
		return this.isAccessor;
	}

	public void setIsAccessor(Boolean isAccessor) {
		this.isAccessor = isAccessor;
	}

	@Override
	public int hashCode() {
		int result = this.id;
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.loc != null ? this.loc.hashCode() : 0);
		result = 31 * result + (this.nrParams != null ? this.nrParams.hashCode() : 0);
		result = 31 * result + (this.isAccessor != null ? this.isAccessor.hashCode() : 0);
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

		MethodEntity that = (MethodEntity) o;

		if (this.id != that.id) {
			return false;
		}
		if (this.name != null ? !this.name.equals(that.name) : that.name != null) {
			return false;
		}
		if (this.loc != null ? !this.loc.equals(that.loc) : that.loc != null) {
			return false;
		}
		if (this.nrParams != null ? !this.nrParams.equals(that.nrParams) : that.nrParams != null) {
			return false;
		}
		if (this.isAccessor != null ? !this.isAccessor.equals(that.isAccessor) : that.isAccessor != null) {
			return false;
		}

		return true;
	}
}

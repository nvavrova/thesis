package db.pojo;

import javax.persistence.*;

/**
 * Created by Nik on 28-07-2015
 */
@Entity
@Table(name = "dependencies", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "DEP_SEQ", sequenceName = "dependencies_id_seq", allocationSize = 1)
public class DependenciesEntity {
	private Integer id;
	private ClassEntity dependent;
	private ClassEntity target;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEP_SEQ")
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "dependent")
	public ClassEntity getDependent() {
		return this.dependent;
	}

	public void setDependent(ClassEntity dependent) {
		this.dependent = dependent;
	}

	@ManyToOne
	@JoinColumn(name = "target")
	public ClassEntity getTarget() {
		return this.target;
	}

	public void setTarget(ClassEntity target) {
		this.target = target;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}

		DependenciesEntity that = (DependenciesEntity) o;

		if (this.id != that.id) {
			return false;
		}

		return true;
	}
}

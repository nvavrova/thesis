package db.pojo;

import javax.persistence.*;

/**
 * Created by Nik on 28-07-2015
 */
@Entity
@Table(name = "class", schema = "public", catalog = "thesis")
@SequenceGenerator(name = "CLASS_SEQ", sequenceName = "class_id_seq", allocationSize = 1)
public class ClassEntity {
	private Integer id;
	private ModuleEntity moduleEntity;
	private String name;
	private Integer loc;
	private Integer lcom;
	private Short nrParents;
	private Short nrPrivateVars;
	private Short nrPublicVars;
	private Short nrUsedGlobals;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASS_SEQ")
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "module_id")
	public ModuleEntity getModuleEntity() {
		return this.moduleEntity;
	}

	public void setModuleEntity(ModuleEntity moduleEntity) {
		this.moduleEntity = moduleEntity;
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
	@Column(name = "lcom")
	public Integer getLcom() {
		return this.lcom;
	}

	public void setLcom(Integer lcom) {
		this.lcom = lcom;
	}

	@Basic
	@Column(name = "nr_parents")
	public Short getNrParents() {
		return this.nrParents;
	}

	public void setNrParents(Short nrParents) {
		this.nrParents = nrParents;
	}

	@Basic
	@Column(name = "nr_private_vars")
	public Short getNrPrivateVars() {
		return this.nrPrivateVars;
	}

	public void setNrPrivateVars(Short nrPrivateVars) {
		this.nrPrivateVars = nrPrivateVars;
	}

	@Basic
	@Column(name = "nr_public_vars")
	public Short getNrPublicVars() {
		return this.nrPublicVars;
	}

	public void setNrPublicVars(Short nrPublicVars) {
		this.nrPublicVars = nrPublicVars;
	}

	@Basic
	@Column(name = "nr_used_globals")
	public Short getNrUsedGlobals() {
		return this.nrUsedGlobals;
	}

	public void setNrUsedGlobals(Short nrUsedGlobals) {
		this.nrUsedGlobals = nrUsedGlobals;
	}

	@Override
	public int hashCode() {
		int result = this.id;
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.loc != null ? this.loc.hashCode() : 0);
		result = 31 * result + (this.lcom != null ? this.lcom.hashCode() : 0);
		result = 31 * result + (this.nrParents != null ? this.nrParents.hashCode() : 0);
		result = 31 * result + (this.nrPrivateVars != null ? this.nrPrivateVars.hashCode() : 0);
		result = 31 * result + (this.nrPublicVars != null ? this.nrPublicVars.hashCode() : 0);
		result = 31 * result + (this.nrUsedGlobals != null ? this.nrUsedGlobals.hashCode() : 0);
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

		ClassEntity that = (ClassEntity) o;

		if (this.id != that.id) {
			return false;
		}
		if (this.name != null ? !this.name.equals(that.name) : that.name != null) {
			return false;
		}
		if (this.loc != null ? !this.loc.equals(that.loc) : that.loc != null) {
			return false;
		}
		if (this.lcom != null ? !this.lcom.equals(that.lcom) : that.lcom != null) {
			return false;
		}
		if (this.nrParents != null ? !this.nrParents.equals(that.nrParents) : that.nrParents != null) {
			return false;
		}
		if (this.nrPrivateVars != null ? !this.nrPrivateVars.equals(that.nrPrivateVars) : that.nrPrivateVars != null) {
			return false;
		}
		if (this.nrPublicVars != null ? !this.nrPublicVars.equals(that.nrPublicVars) : that.nrPublicVars != null) {
			return false;
		}
		if (this.nrUsedGlobals != null ? !this.nrUsedGlobals.equals(that.nrUsedGlobals) : that.nrUsedGlobals != null) {
			return false;
		}

		return true;
	}
}

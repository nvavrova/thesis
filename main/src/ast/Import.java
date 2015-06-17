package ast;

import thesis.Py3TreeVisitor;

import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 25-05-15
 */
public class Import extends Py3Node {

    //TODO: change to use Identifier

    private Map<String, String> modules;

    public Import(LocInfo locInfo) {
        super(locInfo);
    }

    public void addImport(String moduleName) {
        this.modules.put(moduleName, null);
    }

    public void addImport(String moduleName, String alias) {
        this.modules.put(moduleName, alias);
    }

    public Set<String> getModuleNames() {
        return this.modules.keySet();
    }

    public Boolean hasAlias(String moduleName) {
        return this.modules.get(moduleName) != null;
    }

    public String getAlias(String moduleName) {
        assert (this.modules.containsKey(moduleName));
        return this.modules.get(moduleName);
    }

    @Override
    public <T> T accept(Py3TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

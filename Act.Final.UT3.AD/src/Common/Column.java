package Common;

public class Column {
    private String name;
    private String type;
    private boolean allowNull;

    public Column(String name, String type, boolean allowNull) {
        this.name = name;
        this.type = type;
        this.allowNull = allowNull;
    }

    public String getSQL() {
        return name + " " + type + (allowNull ? "" : " NOT NULL");
    }
}


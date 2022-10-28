package polynomial;

public enum VariableName implements Comparable<VariableName> {
    a("a"),
    b("b"),
    c("c"),
    d("d"),
    e("e"),
    f("f"),
    g("g"),
    h("h"),
    i("i"),
    j("j"),
    k("k"),
    l("l"),
    m("m"),
    n("n"),
    o("o"),
    p("p"),
    q("q"),
    r("r"),
    s("s"),
    t("t"),
    u("u"),
    v("v"),
    w("w"),
    x("x"),
    y("y"),
    z("z");

    private final String variableString;

    VariableName(String variableString) {
        this.variableString = variableString;
    }

    @Override
    public String toString() {
        return this.variableString;
    }
}

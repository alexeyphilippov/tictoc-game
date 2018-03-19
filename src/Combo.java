class Combo {
    Cube[] cubes;

    public Combo(Cube... cubes) {
        this.cubes = cubes;
    }

    public boolean isComplete() {
        if (cubes[0].getValue() == " ")
            return false;

        return cubes[0].getValue() == cubes[1].getValue()
                && cubes[1].getValue() == cubes[2].getValue();
    }
}
package brickset;

import repository.Repository;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns the number of LEGO sets with the tag specified.
     *
     * @param tag a LEGO set tag
     * @return the number of LEGO sets with the tag specified
     */
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }

    /**
     * Returns the number of LEGO sets with name.
     *
     * @return the number of LEGO sets with name
     */

    public long countLegoSetsByName() {
        return getAll().stream()
                .filter(legoSet ->! legoSet.getName().isEmpty())
                .count();
    }

    /**
     * Prints the theme of the sets.
     *
     */

    public void printThemesInReverseOrder () {
        getAll().stream()
                .map(LegoSet::getTheme).sorted(Comparator.reverseOrder())
                .distinct()
                .forEach(System.out::println);

    }

    /**
     *  Prints the name of the LEGO sets bigger than the value given.
     *
     * @param piece lego set piece
     */
    public void printNameOfLegoSetsBiggerThanValue (int piece) {
        getAll().stream()
                .filter(legoSet ->  legoSet.getPieces() > piece)
                .map(LegoSet::getName)
                .forEach(System.out::println);
    }

    /**
     * Returns the number of the biggest pieces.
     *
     * @return the number of the biggest pieces
     */
    public long getBiggestPieces () {
        return getAll().stream()
                .mapToInt(LegoSet::getPieces)
                .max().orElse(0);
    }

    /**
     * Prints the LEGO set names starting with the letter P.
     */
    public void printNameStartWithLetterP () {
        getAll().stream()
                .map(LegoSet::getName)
                .filter(legoname -> legoname.charAt(0) == 'P')
                .forEach(System.out::println);
    }


    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.countLegoSetsWithTag("Microscale"));
        System.out.println(repository.countLegoSetsByName());
        repository.printThemesInReverseOrder();
        repository.printNameOfLegoSetsBiggerThanValue(100);
        System.out.println((repository.getBiggestPieces()));
        repository.printNameStartWithLetterP();

    }

}
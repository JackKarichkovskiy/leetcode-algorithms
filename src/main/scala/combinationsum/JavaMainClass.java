package combinationsum;

import java.util.*;
import java.util.stream.Collectors;

public class JavaMainClass {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> result = solution.combinationSum(candidates, 7);
        System.out.println(result);
    }

}

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<Combination> allCombos = methodRec(candidates, target, new Memo());
        return allCombos.stream().map(Combination::toList).collect(Collectors.toList());
    }

    private Set<Combination> methodRec(int[] candidates, int target, Memo memo) {
        Set<Combination> result = new HashSet<>();

        if (target == 0) {
            result.add(new Combination());
            return result;
        }
        if (target < 0) {
            return result;
        }

        for (int candidate : candidates) {
            int nextTarget = target - candidate;
            Set<Combination> combinations = memo.get(nextTarget);
            if (combinations == null) {
                combinations = methodRec(candidates, nextTarget, memo);
                memo.put(nextTarget, combinations);
            }
            result.addAll(addCandidateToCombos(combinations, candidate));
        }
        return result;
    }

    private Set<Combination> addCandidateToCombos(Set<Combination> combos, int candidate) {
        return combos.stream().map(combo -> combo.add(candidate)).collect(Collectors.toSet());
    }
}

class Combination extends HashMap<Integer, Integer> {

    public Combination() {
    }

    public Combination(Map<? extends Integer, ? extends Integer> m) {
        super(m);
    }

    public Combination add(int candidate) {
        Combination newCombo = new Combination(this);
        newCombo.put(candidate, this.getOrDefault(candidate, 0) + 1);
        return newCombo;
    }

    public List<Integer> toList() {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}

class Memo extends HashMap<Integer, Set<Combination>> {

    @Override
    public Set<Combination> put(Integer key, Set<Combination> value) {
        return super.put(key, Set.copyOf(value));
    }

    @Override
    public Set<Combination> get(Object key) {
        return Optional.ofNullable(super.get(key))
                .map(HashSet::new)
                .orElse(null);
    }
}
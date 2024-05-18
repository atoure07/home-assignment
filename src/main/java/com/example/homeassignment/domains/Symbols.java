package com.example.homeassignment.domains;

public enum Symbols {
    A {
        @Override
        public double reward_impact(int amount) {
            return 50 * amount;
        }
    },
    B {
        public double reward_impact(int amount) {
            return 25 * amount;
        }
    },
    C {
        public double reward_impact(int amount) {
            return 10 * amount;
        }
    },
    D {
        public double reward_impact(int amount) {
            return 5 * amount;
        }
    },
    E {
        public double reward_impact(int amount) {
            return 3 * amount;
        }
    },
    F {
        public double reward_impact(int amount) {
            return 1.5 * amount;
        }
    },

    TEN_X {
        public double reward_impact(int amount) {
            return 10 * amount;
        }
    },
    FIVE_X {
        public double reward_impact(int amount) {
            return 5 * amount;
        }
    },
    ADD_THOUSAND {
        public double reward_impact(int amount) {
            return 1000 + amount;
        }
    },
    ADD_FIVE_HUNDRED {
        public double reward_impact(int amount) {
            return 500 + amount;
        }
    },
    MISS {
        public double reward_impact(int amount) {
            return amount;
        }
    };

    public abstract double reward_impact(int amount);
}

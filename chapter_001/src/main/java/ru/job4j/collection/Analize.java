package ru.job4j.collection;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Set<User> prevUsers = new HashSet<>(previous);
        Set<Integer> prevId = previous.stream()
                .map(u -> u.id)
                .collect(Collectors.toSet());
        for (User u : current) {
            if (prevUsers.contains(u)) {
                prevUsers.remove(u);
                continue;
            }
            if (prevId.contains(u.id)) {
                info.incChanged();
            } else {
                info.incAdded();
            }
        }
        info.deleted = (prevUsers.size() - info.changed);
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public Info() {
            this(0, 0, 0);
        }

        public void incAdded() {
            this.added++;
        }

        public void incChanged() {
            this.changed++;
        }

        public void incDeleted() {
            this.deleted++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
package kotlin.reflect.jvm.internal.impl.utils;

import defpackage.acqr;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DFS {

    public interface Neighbors<N> {
        Iterable<? extends N> getNeighbors(N n);
    }

    public interface NodeHandler<N, R> {
        void afterChildren(N n);

        boolean beforeChildren(N n);

        R result();
    }

    public interface Visited<N> {
        boolean checkAndMarkVisited(N n);
    }

    public abstract class AbstractNodeHandler<N, R> implements NodeHandler<N, R> {
        public void afterChildren(N n) {
        }

        public boolean beforeChildren(N n) {
            return true;
        }
    }

    public class VisitedWithSet<N> implements Visited<N> {
        private final Set<N> visited;

        public VisitedWithSet() {
            this(new HashSet());
        }

        public VisitedWithSet(Set<N> set) {
            this.visited = set;
        }

        public boolean checkAndMarkVisited(N n) {
            return this.visited.add(n);
        }
    }

    public abstract class CollectingNodeHandler<N, R, C extends Iterable<R>> extends AbstractNodeHandler<N, C> {
        protected final C result;

        protected CollectingNodeHandler(C c) {
            this.result = c;
        }

        public C result() {
            return this.result;
        }
    }

    public abstract class NodeHandlerWithListResult<N, R> extends CollectingNodeHandler<N, R, LinkedList<R>> {
        protected NodeHandlerWithListResult() {
            super(new LinkedList());
        }
    }

    public static <N, R> R dfs(Collection<N> collection, Neighbors<N> neighbors, Visited<N> visited, NodeHandler<N, R> nodeHandler) {
        for (N doDfs : collection) {
            doDfs(doDfs, neighbors, visited, nodeHandler);
        }
        return nodeHandler.result();
    }

    public static <N, R> R dfs(Collection<N> collection, Neighbors<N> neighbors, NodeHandler<N, R> nodeHandler) {
        return dfs(collection, neighbors, new VisitedWithSet(), nodeHandler);
    }

    public static <N> Boolean ifAny(Collection<N> collection, Neighbors<N> neighbors, final acqr<N, Boolean> acqr) {
        final boolean[] zArr = new boolean[1];
        return (Boolean) dfs(collection, neighbors, new AbstractNodeHandler<N, Boolean>() {
            public final boolean beforeChildren(N n) {
                if (((Boolean) acqr.invoke(n)).booleanValue()) {
                    zArr[0] = true;
                }
                return !zArr[0];
            }

            public final Boolean result() {
                return Boolean.valueOf(zArr[0]);
            }
        });
    }

    public static <N> void doDfs(N n, Neighbors<N> neighbors, Visited<N> visited, NodeHandler<N, ?> nodeHandler) {
        if (visited.checkAndMarkVisited(n) && nodeHandler.beforeChildren(n)) {
            for (Object doDfs : neighbors.getNeighbors(n)) {
                doDfs(doDfs, neighbors, visited, nodeHandler);
            }
            nodeHandler.afterChildren(n);
        }
    }
}

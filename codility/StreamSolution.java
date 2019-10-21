package codility;

public class StreamSolution {

    public static Stream<PendingTransaction> reconcile(Stream<PendingTransaction> pending, Stream<Stream<ProcessedTransaction>> processed) {
        if(pending == null || processed == null) {
            return Stream.empty();
        }
        List<Long>  filteredProcessedId = processed
                .flatMap(p -> p)
                .filter(Objects::nonNull)
                .filter(p -> p.getStatus() != null && p.getStatus().isPresent() && "DONE".equalsIgnoreCase(p.getStatus().get()))
                .filter(p -> p.getId() != null && p.getId().length() > 0 && pending.anyMatch(pp -> pp.equals(p.getId())))
                .map(p -> Long.parseLong(p.getId())).collect(Collectors.toList());

        return pending.filter(p -> filteredProcessedId.contains(p.getId()));
    }

}
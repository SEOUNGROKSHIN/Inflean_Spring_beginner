package com.greedy.hellospring.repository;

import com.greedy.hellospring.domain.Board;

import java.util.*;

public class MemoryBoardRepository implements BoardRepository{

    private static Map<Long , Board> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Board save(Board board) {
        board.setTno(++sequence);
        store.put(board.getTno() , board);
        return board;
    }

    @Override
    public Optional<Board> findByTno(Long tno) {

        return Optional.ofNullable(store.get(tno));
    }

    @Override
    public Optional<Board> findByTitle(String title) {

        return store.values().stream()
                .filter(board -> board.getTitle().equals(title)).findAny();
    }

    @Override
    public Optional<Board> findByContent(String content) {
        return store.values().stream()
                .filter(board -> board.getContent().equals(content)).findAny();
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

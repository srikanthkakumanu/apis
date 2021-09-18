package com.apidemo.rest.repos;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.apidemo.rest.model.ToDo;

import org.springframework.stereotype.Repository;

/**
 * ToDo Data (DB) Repository
 */
@Repository
public class ToDoRepository implements CommonRepository<ToDo> {

    private Map<String, ToDo> todos = new HashMap<>();

    private Comparator<Map.Entry<String, ToDo>> entryComparator = 
                (Map.Entry<String, ToDo> first, Map.Entry<String, ToDo> second) -> {
                    return first.getValue().getCreated().compareTo(second.getValue().getCreated());
                };
    /**
     * Saves ToDo
     * @param todo todo model
     */
    @Override
    public ToDo save(ToDo todo) {
        ToDo result = todos.get(todo.getId());
        // todo exist
        if(result != null) {
            result.setId(todo.getId());
            result.setDescription(todo.getDescription());
            result.setCompleted(todo.isCompleted());
            todo = result;
        }
        // todo not exist
        todos.put(todo.getId(), todo);
        return todos.get(todo.getId());
    }

    /**
     * Saves collection of ToDo's
     * @param todos collection of todos
     */
    @Override
    public Iterable<ToDo> save(Collection<ToDo> models) {
        models.forEach(this::save); // saves each todo of models collection
        return findAll();
    }

    /**
     * Deletes a ToDo
     * @param todo a todo model
     */
    @Override
    public void delete(ToDo todo) {
        todos.remove(todo.getId());
    }

    /**
     * Finds a Todo by Id and returns
     * @param id An id of ToDo
     */
    @Override
    public ToDo findById(String id) {
        return todos.get(id);
    }

    /**
     * Finds and returns all ToDo's after comparision
     */
    @Override
    public Iterable<ToDo> findAll() {
        return todos.entrySet().stream().sorted(entryComparator).map(Map.Entry::getValue).collect(Collectors.toList());
    }
}

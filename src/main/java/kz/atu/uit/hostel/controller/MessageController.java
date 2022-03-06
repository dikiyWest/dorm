package kz.atu.uit.hostel.controller;

import kz.atu.uit.hostel.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private int counter = 3;
    public List<Map<String,String >> messages = new ArrayList<Map<String,String >>(){{
        add(new HashMap<String,String>(){{ put("id","1");put("text","first"); }});
        add(new HashMap<String,String>(){{ put("id","2");put("text","second"); }});
        add(new HashMap<String,String>(){{ put("id","3");put("text","third"); }});
    }};


    @GetMapping
    public List<Map<String,String >> index(){
        return messages;
    }
    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        return getMessage(id);
    }

    public Map<String,String> getMessage(@PathVariable String id){
        return messages.stream()
                .filter(message->message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public  Map<String,String> create(@RequestBody Map<String,String> message){
        System.out.println("sadadasdas");
        message.put("id",String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public  Map<String,String> update(@PathVariable String  id,@RequestBody Map<String,String> message){
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id",id);

        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String,String> message = getMessage(id);
        messages.remove(message);
    }
    
}

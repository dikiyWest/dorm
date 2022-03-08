package kz.atu.uit.hostel.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kz.atu.uit.hostel.domain.Message;
import kz.atu.uit.hostel.domain.Views;
import kz.atu.uit.hostel.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }





    @GetMapping
    @JsonView(Views.idName.class)
    public List<Message> index(){
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }



    @PostMapping
    public Message create(
            @RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
      return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageDb,
                          @RequestBody Message message){
        BeanUtils.copyProperties(message,messageDb,"id");
        return messageRepo.save(messageDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);
    }
    
}

package com.example.challenge.UserTrackService.services;

import com.example.challenge.UserTrackService.exception.TrackNotFoundException;
import com.example.challenge.UserTrackService.exception.UserAlreadyExistsException;
import com.example.challenge.UserTrackService.exception.UserNotFoundException;
import com.example.challenge.UserTrackService.model.Track;
import com.example.challenge.UserTrackService.model.User;
import com.example.challenge.UserTrackService.proxy.UserProxy;
import com.example.challenge.UserTrackService.rabbitmq.CommonUser;
import com.example.challenge.UserTrackService.rabbitmq.Producer;
import com.example.challenge.UserTrackService.rabbitmq.UserDTO;
import com.example.challenge.UserTrackService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserProxy userProxy;
    @Autowired
    private Producer producer;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy){
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }
    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        User addedUser = userRepository.save(user);
        if(!(addedUser.getUserId() == 0)){
            ResponseEntity re = userProxy.saveUser(user);
        }
        return addedUser;
    }

    @Override
    public User addTrackToPlaylist(int userId, Track track) throws UserNotFoundException {
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findByUserId(userId);
        if(user.getTrackList() == null){
            user.setTrackList(Arrays.asList(track));
        }else {
            List<Track> tracks = user.getTrackList();
            tracks.add(track);
            user.setTrackList(tracks);
        }
        return userRepository.save(user);
    }

    @Override
    public User deleteTrackFromPlaylist(int userId, int trackId) throws UserNotFoundException, TrackNotFoundException {

        boolean result = false;
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        List<Track> tracks = user.getTrackList();
        result = tracks.removeIf(x -> x.getTrackId() == trackId);
        if(!result){
            throw new TrackNotFoundException();
        }
        user.setTrackList(tracks);
        return userRepository.save(user);
    }

    @Override
    public List<Track> getAllTrackFromPlaylist(int userId) throws UserNotFoundException {
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(userId).get().getTrackList();
    }


    @Override
    public User updateTrackInPlaylist(int userId, Track track) throws  UserNotFoundException {

      if(userRepository.findById(userId).isEmpty()){
          throw  new UserNotFoundException();
      }
      User user = userRepository.findById(userId).get();
      List<Track> tracks = user.getTrackList();
        Iterator<Track>  iterator = tracks.iterator();
        while (iterator.hasNext()){
            Track track1 = iterator.next();
            if(track1.getTrackId() == track.getTrackId()){
               track1.setTrackName(track.getTrackName());
               track1.setArtistName(track.getArtistName());
            }
        }

        user.setTrackList(tracks);
        return userRepository.save(user);
    }

    @Override
    public User addUser1(CommonUser commonUser) {
        UserDTO userDTO = new UserDTO(commonUser.getUserId(), commonUser.getPassword());
        producer.sendDtoToQueue(userDTO);

        User user = new User(commonUser.getUserId(), commonUser.getUserName(),commonUser.getPassword(),new ArrayList<>());
        return userRepository.insert(user);
    }
}

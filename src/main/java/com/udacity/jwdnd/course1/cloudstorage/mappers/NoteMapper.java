package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;

@Mapper
public interface NoteMapper {
    
    @Insert ("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);


    @Update ("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    int updateNote (Note note);

    @Select ("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNote (Integer noteId);


    @Delete ("DELETE FROM NOTES WHERE noteid = #{noteId}")
    int deleteNote (Integer noteId);

    @Select ("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getNotesByUserId(Integer userId);

    @Select ("SELECT * FROM NOTES")
    List<Note> getNotes();

    @Delete ("DELETE FROM NOTES WHERE userid = #{userId}")
    int deleteallNotes(Integer userId);
}

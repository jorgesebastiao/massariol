package br.com.massariol.application.students;

import br.com.massariol.application.students.commands.StudentCreateCommand;
import br.com.massariol.application.students.commands.StudentUpdateCommand;
import br.com.massariol.domain.features.exceptions.ExceptionCpfInUse;
import br.com.massariol.domain.features.students.Student;
import br.com.massariol.infrastructure.repositories.students.StudentRepository;
import br.com.massariol.infrastructure.repositories.students.StudentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentAppServiceImpl implements StudentAppService {
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    public StudentAppServiceImpl(ModelMapper modelMapper, StudentRepository studentRepository) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }

    public Page<Student> findAll(Pageable pageable, String filter) {
        return studentRepository.findAll(StudentSpecification.filter(filter),pageable);
    }

    public Student getById(Long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Student findByCpf(String cpf) {
        return studentRepository.findByPersonCpf(cpf)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Long add(StudentCreateCommand studentCreateCommand) {
        var student = modelMapper.map(studentCreateCommand, Student.class);

        if(studentRepository.existsByPersonCpf(student.getPerson().getCpf()))
            throw  new ExceptionCpfInUse();

        studentRepository.save(student);
        return  student.getId();
    }

    public void update(StudentUpdateCommand studentUpdateCommand) {
        var studentDatabase = studentRepository.findById(studentUpdateCommand.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        modelMapper.map(studentUpdateCommand, studentDatabase);

        studentRepository.save(studentDatabase);
    }

    public void signature(Long studentId, String signature) {
        var studentDatabase = studentRepository.findById(studentId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        studentDatabase.getPerson().setSignaturePicture(signature.split(",")[1].getBytes());
        studentRepository.save(studentDatabase);
    }
}

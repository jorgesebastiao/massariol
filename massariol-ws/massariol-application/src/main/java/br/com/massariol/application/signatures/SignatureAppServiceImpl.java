package br.com.massariol.application.signatures;

import br.com.massariol.application.instructors.InstructorAppService;
import br.com.massariol.application.signatures.commands.SignatureCreateCommand;
import br.com.massariol.application.signatures.queries.SignatureGetEntityQuery;
import br.com.massariol.application.students.StudentAppService;
import br.com.massariol.application.supervisors.SupervisorAppService;
import br.com.massariol.domain.features.signatures.Signature;
import br.com.massariol.domain.features.signatures.SignatureType;
import org.springframework.stereotype.Service;

@Service
public class SignatureAppServiceImpl implements SignatureAppService {
    private InstructorAppService instructorAppService;
    private StudentAppService studentAppService;
    private SupervisorAppService supervisorAppService;

    public SignatureAppServiceImpl(InstructorAppService instructorAppService, StudentAppService studentAppService, SupervisorAppService supervisorAppService) {
        this.instructorAppService = instructorAppService;
        this.studentAppService = studentAppService;
        this.supervisorAppService = supervisorAppService;
    }

    public Signature findByCpfAndType(SignatureGetEntityQuery signatureGetEntityQuery) {
        if (signatureGetEntityQuery.getType() == SignatureType.INSTRUCTOR)
            return instructorAppService.findByCpf(signatureGetEntityQuery.getCpf());
        else if (signatureGetEntityQuery.getType() == SignatureType.STUDENT)
            return studentAppService.findByCpf(signatureGetEntityQuery.getCpf());
        else
            return supervisorAppService.findByCpf(signatureGetEntityQuery.getCpf());
    }

    public void add(SignatureCreateCommand signatureCreateCommand) {
        if (signatureCreateCommand.getType() == SignatureType.INSTRUCTOR)
             instructorAppService.signature(signatureCreateCommand.getEntityId(),signatureCreateCommand.getSignature());
        else if (signatureCreateCommand.getType() == SignatureType.STUDENT)
            studentAppService.signature(signatureCreateCommand.getEntityId(),signatureCreateCommand.getSignature());
        else
            supervisorAppService.signature(signatureCreateCommand.getEntityId(),signatureCreateCommand.getSignature());
    }
}

package br.com.massariol.distribution.controllers.signatures;

import br.com.massariol.application.signatures.SignatureAppService;
import br.com.massariol.application.signatures.commands.SignatureCreateCommand;
import br.com.massariol.application.signatures.queries.SignatureGetEntityQuery;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.signatures.viewmodels.SignatureViewModel;
import br.com.massariol.domain.features.signatures.SignatureType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/signatures")
@Api(value="signatures" ,description="signatures")
public class SignatureController extends ApiBaseController {
    private  final SignatureAppService signatureAppService;
    public SignatureController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, SignatureAppService signatureAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.signatureAppService = signatureAppService;
    }

    @ApiOperation(value = "View a entity by cpf and type", response = SignatureViewModel.class)
    @GetMapping("/{type}/cpf/{cpf}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public ResponseEntity<SignatureViewModel> getEntityByCpf(@PathVariable SignatureType type, @PathVariable String cpf) {
        var signatureGetEntityQuery = SignatureGetEntityQuery.builder().cpf(cpf).type(type).build();
        return  ok(sourceToDestination(signatureAppService.findByCpfAndType(signatureGetEntityQuery), SignatureViewModel.class));
    }

    @ApiOperation(value = "signature of entities")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity postSignature(@RequestBody SignatureCreateCommand signatureCreateCommand) {
        signatureAppService.add(signatureCreateCommand);
        return status(HttpStatus.CREATED).build();
    }
}

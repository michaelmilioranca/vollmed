package med.voll.api.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.repository.medico.EspecialidadeEnum;

public class AgendamentoUtil {

    public static DadosAgendamentoConsultaInput.DadosAgendamentoConsultaInputBuilder agendamentoInputBuilder() {
        return DadosAgendamentoConsultaInput.builder()
                .idMedico(1L)
                .idPaciente(1L)
                .data(LocalDateTime.now().with(DayOfWeek.MONDAY).withHour(13))
                .especialidade(EspecialidadeEnum.DERMATOLOGIA);
    }
}

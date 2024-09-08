package med.voll.api.repository.medico;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(
            """
            SELECT m
            FROM Medico m
            WHERE m.ativo = true
            and m.especialidade = :especialidade
            and not exists (SELECT 1 FROM Consulta c where c.medico = m and c.data = :data)
            order by rand()
            limit 1
            """)
    Optional<Medico> buscarMedicoAleatorioLivrePorEspecialidadeEData(
            EspecialidadeEnum especialidade, LocalDateTime data);
}

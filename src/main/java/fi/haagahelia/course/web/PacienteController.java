package fi.haagahelia.course.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.Motivo;
import fi.haagahelia.course.domain.MotivoRepository;
import fi.haagahelia.course.domain.Citas;
import fi.haagahelia.course.domain.PacienteRepository;

@Controller
public class PacienteController {
	@Autowired
	private PacienteRepository repository;

	@Autowired
	private MotivoRepository mrepository;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/paciente")
	public String index(Model model) {
		List<Citas> paciente = (List<Citas>) repository.findAll();
		model.addAttribute("paciente", paciente);
		return "paciente";
	}

	@RequestMapping(value = "add")
	public String addPaciente(Model model) {
		model.addAttribute("paciente", new Citas());
		return "addPaciente";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editPaciente(@PathVariable("id") Long pacienteId, Model model) {
		Optional<Citas> pacienteOptional = repository.findById(pacienteId);
		Citas paciente = pacienteOptional.orElse(new Citas());
		model.addAttribute("paciente", paciente);
		return "editPaciente";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	  public String save(@RequestParam(value = "action", required = true) String action, Citas paciente) {
        if (action.equalsIgnoreCase("save")) {
            repository.save(paciente);
        }
        return "redirect:/paciente";
    }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePaciente(@PathVariable("id") Long pacienteId, Model model) {
		repository.deleteById(pacienteId);
		return "redirect:/paciente";
	}

	@RequestMapping(value = "addpacientemotivo/{id}", method = RequestMethod.GET)
	public String addMotivo(@PathVariable("id") Long pacienteId, Model model) {

		model.addAttribute("motivo", mrepository.findAll());
		model.addAttribute("paciente", repository.findById(pacienteId).get());
		return "addPacienteMotivo";
	}

	@RequestMapping(value = "/paciente/{id}/motivo", method = RequestMethod.GET)
	public String pacienteAddMotivo(@RequestParam(value = "action", required = true) String action,
			@PathVariable Long id, @RequestParam Long motivoId, Model model) {
		Optional<Motivo> motivo = mrepository.findById(motivoId);
		Optional<Citas> paciente = repository.findById(id);

		if (paciente.isPresent() && action.equalsIgnoreCase("save")) {
			if (!paciente.get().hasMotivo(motivo.get())) {
				paciente.get().getMotivo().add(motivo.get());
			}
			repository.save(paciente.get());
			model.addAttribute("paciente", mrepository.findById(id));
			model.addAttribute("motivo", mrepository.findAll());
			return "redirect:/paciente";
		}

		model.addAttribute("developers", repository.findAll());
		return "redirect:/paciente";

	}

	@RequestMapping(value = "getpaciente", method = RequestMethod.GET)
	public @ResponseBody List<Citas> getPaciente() {
		return (List<Citas>) repository.findAll();
	}
}

package br.com.caelum.financas.modelo;


import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;


@Startup
@Singleton
public class Agendador {

	@Resource
	private TimerService timerService;
	
	public void agenda(String expressaoMinuto, String expressaoSegundo){
		
		ScheduleExpression expression = new ScheduleExpression();
		expression.hour("*");
		expression.minute(expressaoMinuto);
		expression.second(expressaoSegundo);
		
		TimerConfig config = new TimerConfig();
		config.setInfo(expression.toString());
		config.setPersistent(false);
		
		this.timerService.createCalendarTimer(expression,config);
		
		System.out.println("Agendamento: " + expression);
		
	}
	
	@Timeout
	public void verificacaoPeriodicaSeHaNovasHoras(Timer timer){
			System.out.println(timer.getInfo());
	}
}

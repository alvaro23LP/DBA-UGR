package practica3;

import jade.core.behaviours.Behaviour;
import java.util.ArrayList;

public class MejorMovimientoBehaviour extends Behaviour {
    Entorno entorno;
    double mejorDistancia;
    int filaMovimiento, colMovimiento;
    Agente agente;

    MovimientoNorte movNorte;
    MovimientoSur movSur;
    MovimientoEste movEste;
    MovimientoOeste movOeste;
    MovimientoNoreste movNoreste;
    MovimientoNoroeste movNoroeste;
    MovimientoSureste movSureste;
    MovimientoSuroeste movSuroeste;

    public MejorMovimientoBehaviour(Entorno entornoAgente, Agente agente) {
        this.entorno = entornoAgente;
        this.agente = agente;
        movNorte = new MovimientoNorte(agente);
        movSur = new MovimientoSur(agente);
        movEste = new MovimientoEste(agente);
        movOeste = new MovimientoOeste(agente);
        movNoreste = new MovimientoNoreste(agente);
        movNoroeste = new MovimientoNoroeste(agente);
        movSureste = new MovimientoSureste(agente);
        movSuroeste = new MovimientoSuroeste(agente);
        mejorDistancia = Double.MAX_VALUE;

    }

    @Override
    public void action() {
        if (!agente.buscandoRenos && !agente.buscandoSanta || agente.stopMovimiento) {
            return;
        }
        mejorDistancia = Double.MAX_VALUE;

        // Limpiamos la percepciones si tenia
        if (agente.actualizarVistaAlrededor != null)
            agente.actualizarVistaAlrededor.clear();

        // Actualizamos la percepcion del agente
        agente.actualizarVistaAlrededor = entorno.actualizarEntornoAgente();

        // Comprobamos movimiento Norte, devuelve -1 si no puede moverse
        double norte = movNorte.calculaValorMovimiento();
        if (norte != -1) {
            if (norte < mejorDistancia) {
                mejorDistancia = norte;
                filaMovimiento = movNorte.getFila();
                colMovimiento = movNorte.getColumna();
            }
        }

        // Comprobamos movimiento Este, devuelve -1 si no puede moverse
        double este = movEste.calculaValorMovimiento();
        if (este != -1) {
            if (este < mejorDistancia) {
                mejorDistancia = este;
                filaMovimiento = movEste.getFila();
                colMovimiento = movEste.getColumna();
            }
        }

        // Comprobamos movimiento Sur, devuelve -1 si no puede moverse
        double sur = movSur.calculaValorMovimiento();
        if (sur != -1) {
            if (sur < mejorDistancia) {
                mejorDistancia = sur;
                filaMovimiento = movSur.getFila();
                colMovimiento = movSur.getColumna();
            }
        }

        // Comprobamos movimiento Oeste, devuelve -1 si no puede moverse
        double oeste = movOeste.calculaValorMovimiento();
        if (oeste != -1) {
            if (oeste < mejorDistancia) {
                mejorDistancia = oeste;
                filaMovimiento = movOeste.getFila();
                colMovimiento = movOeste.getColumna();
            }
        }

        // Comprobamos movimiento Noreste, devuelve -1 si no puede moverse
        double norEste = movNoreste.calculaValorMovimiento();
        if (norEste != -1) {
            if (norEste < mejorDistancia) {
                mejorDistancia = norEste;
                filaMovimiento = movNoreste.getFila();
                colMovimiento = movNoreste.getColumna();
            }
        }

        // Comprobamos movimiento Noroeste, devuelve -1 si no puede moverse
        double norOeste = movNoroeste.calculaValorMovimiento();
        if (norOeste != -1) {
            if (norOeste < mejorDistancia) {
                mejorDistancia = norOeste;
                filaMovimiento = movNoroeste.getFila();
                colMovimiento = movNoroeste.getColumna();
            }
        }

        // Comprobamos movimiento Sureste, devuelve -1 si no puede moverse
        double surEste = movSureste.calculaValorMovimiento();
        if (surEste != -1) {
            if (surEste < mejorDistancia) {
                mejorDistancia = surEste;
                filaMovimiento = movSureste.getFila();
                colMovimiento = movSureste.getColumna();
            }
        }

        // Comprobamos movimiento Suroeste, devuelve -1 si no puede moverse
        double surOeste = movSuroeste.calculaValorMovimiento();
        if (surOeste != -1) {
            if (surOeste < mejorDistancia) {
                mejorDistancia = surOeste;
                filaMovimiento = movSuroeste.getFila();
                colMovimiento = movSuroeste.getColumna();
            }
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.print(e);
        }

        // Forzamos a que pase el muro diagonal una vez que lo haya rodeado
        if (agente.dejarDeSeguirDiagonal) {
            if (agente.caminoRecorrido.size() > 2) {
                agente.dejarDeSeguirDiagonal = false;

                // Cojemos la ultima posicion del camino recorrido para saber en que direccion
                // la estaba recorriendo
                ArrayList<Integer> ultima_pos = agente.caminoRecorrido.get(agente.caminoRecorrido.size() - 2);
                int x = ultima_pos.get(0);
                int y = ultima_pos.get(1);

                // Suroeste
                if (agente.filaAgente - 1 == x && agente.columnaAgente + 1 == y) {
                    // Suroeste muro arriba
                    if (agente.actualizarVistaAlrededor.get(1) == -1) {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NOROESTE)) {
                            filaMovimiento = agente.filaAgente - 1;
                            colMovimiento = agente.columnaAgente - 1;
                        }
                    }
                    // Suroeste caso contrario
                    else if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SURESTE)) {
                        filaMovimiento = agente.filaAgente + 1;
                        colMovimiento = agente.columnaAgente + 1;
                    }

                    agente.dejarDeSeguirDiagonal = false;

                }

                // Sureste
                if (agente.filaAgente - 1 == x && agente.columnaAgente - 1 == y) {
                    // Sureste muro arriba
                    if (agente.actualizarVistaAlrededor.get(1) == -1) {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NORESTE)) {
                            filaMovimiento = agente.filaAgente - 1;
                            colMovimiento = agente.columnaAgente + 1;
                        }
                    }
                    // Sureste caso contrario
                    else if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SUROESTE)) {
                        filaMovimiento = agente.filaAgente + 1;
                        colMovimiento = agente.columnaAgente - 1;
                    }

                    agente.dejarDeSeguirDiagonal = false;
                }

                // Noroeste
                if (agente.filaAgente + 1 == x && agente.columnaAgente + 1 == y) {
                    // Noroeste muro derecha
                    if (agente.actualizarVistaAlrededor.get(3) == -1) {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NORESTE)) {
                            filaMovimiento = agente.filaAgente - 1;
                            colMovimiento = agente.columnaAgente + 1;
                        }
                    }
                    // Noroeste caso contrario
                    else if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SUROESTE)) {
                        filaMovimiento = agente.filaAgente + 1;
                        colMovimiento = agente.columnaAgente - 1;
                    }

                    agente.dejarDeSeguirDiagonal = false;
                }

                // Noreste
                if (agente.filaAgente + 1 == x && agente.columnaAgente - 1 == y) {
                    // Noreste muro arriba
                    if (agente.actualizarVistaAlrededor.get(7) == -1) {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NOROESTE)) {
                            filaMovimiento = agente.filaAgente - 1;
                            colMovimiento = agente.columnaAgente - 1;
                        }
                    }
                    // Noreste caso contrario
                    else if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SURESTE)) {
                        filaMovimiento = agente.filaAgente + 1;
                        colMovimiento = agente.columnaAgente + 1;
                    }

                    agente.dejarDeSeguirDiagonal = false;
                }
            }
        }

        // forzamos a que pase el muro vertical/horizontal una vez que lo haya rodeado
        if (agente.dejarDeSeguirPared) {
            if (agente.caminoRecorrido.size() > 2) {
                agente.dejarDeSeguirPared = false;

                // Cojemos la ultima posicion del camino recorrido para saber en que direccion
                // la estaba recorriendo
                ArrayList<Integer> ultima_pos = agente.caminoRecorrido.get(agente.caminoRecorrido.size() - 2);
                int x = ultima_pos.get(0);
                int y = ultima_pos.get(1);

                // Norte
                if (agente.filaAgente + 1 == x && agente.columnaAgente == y) {
                    // Suroeste muro izquierda
                    if (agente.actualizarVistaAlrededor.get(7) == -1) {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NOROESTE)) {
                            filaMovimiento = agente.filaAgente - 1;
                            colMovimiento = agente.columnaAgente - 1;
                        }
                    } else {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NORESTE)) {
                            filaMovimiento = agente.filaAgente - 1;
                            colMovimiento = agente.columnaAgente + 1;
                        }
                    }
                    agente.dejarDeSeguirPared = false;

                }

                // Oeste
                if (agente.filaAgente == x && agente.columnaAgente + 1 == y
                        && agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SUROESTE)) {
                    // Sureste muro abajo
                    if (agente.actualizarVistaAlrededor.get(5) == -1) {
                        filaMovimiento = agente.filaAgente + 1;
                        colMovimiento = agente.columnaAgente - 1;
                    }
                    agente.dejarDeSeguirPared = false;
                }

                // Este
                if (agente.filaAgente == x && agente.columnaAgente - 1 == y) {
                    // Noroeste muro abajo
                    if (agente.actualizarVistaAlrededor.get(5) == -1) {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SURESTE)) {
                            filaMovimiento = agente.filaAgente + 1;
                            colMovimiento = agente.columnaAgente + 1;
                        }

                    } else {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NORESTE)) {
                            filaMovimiento = agente.filaAgente - 1;
                            colMovimiento = agente.columnaAgente + 1;
                        }
                    }

                    agente.dejarDeSeguirPared = false;
                }

                // Sur
                if (agente.filaAgente - 1 == x && agente.columnaAgente == y) {
                    // Noreste muro derecha
                    if (agente.actualizarVistaAlrededor.get(3) == -1) {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SURESTE)) {
                            filaMovimiento = agente.filaAgente + 1;
                            colMovimiento = agente.columnaAgente + 1;
                        }
                    } else {
                        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SUROESTE)) {
                            filaMovimiento = agente.filaAgente + 1;
                            colMovimiento = agente.columnaAgente - 1;
                        }
                    }

                    agente.dejarDeSeguirPared = false;
                }
            }
        }

        agente.filMovimiento = filaMovimiento;
        agente.colMovimiento = colMovimiento;

        
    }

    @Override
    public boolean done() {
        return (agente.filaAgente == agente.filaMeta && agente.columnaAgente == agente.columnaMeta && agente.buscandoSanta);
    }
}

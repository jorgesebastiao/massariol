export class SignatureCreateCommand {
  public type: number;
  public entityId: number;
  public signature: string;

  constructor(type: number, entityId: number, signature: string) {
    this.type = type;
    this.entityId = entityId;
    this.signature = signature;
  }
}

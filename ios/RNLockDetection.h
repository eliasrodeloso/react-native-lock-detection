#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

@interface RNLockDetection : RCTEventEmitter <RCTBridgeModule>

- (void)lockStatusChanged:(NSString *)newStatus;

@end

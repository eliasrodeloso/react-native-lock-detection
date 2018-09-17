
#import "RNLockDetection.h"
#import <React/RCTLog.h>

RNLockDetection * refToSelf;

//call back
static void displayStatusChanged(CFNotificationCenterRef center, void *observer, CFStringRef name, const void *object, CFDictionaryRef userInfo)
{
    // the "com.apple.springboard.lockcomplete" notification will always come after the "com.apple.springboard.lockstate" notification

    NSString *lockState = (__bridge NSString*)name;
    RCTLogInfo(@"Darwin notification NAME = %@",name);

    if([lockState isEqualToString:@"com.apple.springboard.lockcomplete"])
    {
        [refToSelf lockStatusChanged:@"LOCKED"];
        RCTLogInfo(@"DEVICE LOCKED");
    }
    else
    {
        [refToSelf lockStatusChanged:@"NOT_LOCKED"];
        RCTLogInfo(@"LOCK STATUS CHANGED");
    }   
}


@implementation RNLockDetection

- (id) init
{
    self = [super init];
    refToSelf = self;
    return self;
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE(LockDetection);

// Register the events for React Native
- (NSArray<NSString *> *)supportedEvents
{
  return @[@"LockStatusChange"];
}

// function called to send the event to JavaScript
- (void)lockStatusChanged:(NSString *)newStatus
{
  NSString *newLockedStatus = newStatus;
  [self sendEventWithName:@"LockStatusChange" body:@{@"newStatus": newLockedStatus}];
}

RCT_EXPORT_METHOD(registerforDeviceLockNotif) {
    RCTLogInfo(@"Registering for lock events");
    //Screen lock notifications
    CFNotificationCenterAddObserver(CFNotificationCenterGetDarwinNotifyCenter(), NULL, displayStatusChanged, CFSTR("com.apple.springboard.lockcomplete"), NULL,CFNotificationSuspensionBehaviorDeliverImmediately);

    CFNotificationCenterAddObserver(CFNotificationCenterGetDarwinNotifyCenter(), NULL, displayStatusChanged, CFSTR("com.apple.springboard.lockstate"), NULL, CFNotificationSuspensionBehaviorDeliverImmediately);
}

@end
